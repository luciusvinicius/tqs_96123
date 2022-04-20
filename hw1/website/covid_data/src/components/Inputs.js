import React from "react"
import { Autocomplete, CircularProgress, TextField } from '@mui/material';
import { useEffect, useState } from 'react';
import DataShown from './DataShown';

import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

const Inputs = ({api}) => {

    const BASE_URL = "http://localhost:8080"

    const [countries, setCountries] = useState([])
    const [countryName, setCountryName] = useState("")
    const [data, setData] = useState({})
    const [isFullLoaded, setIsFullLoaded] = useState(false)
    const [startDateVal, setStartDateVal] = useState(new Date())
    const [endDateVal, setEndDateVal] = useState(new Date())

  
    const createRequest = (uri, setFunc, changeLoad=false) => {

        if (changeLoad) {
            setIsFullLoaded(false)
        }

        fetch(uri, {
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
                // // "Origin":"frontend:3000"
            }
        })
        
        .then(response => response.json())
        .then(response => {
            console.log("response", response)
            if (api == "api1") {
                setFunc(response.response)
            }
            else {
                setFunc(response.data.map(c => c.name))
            }

            if (changeLoad) {
            setIsFullLoaded(true)
            }
        })
        .catch(error => {
            console.log("sussy error", error)
        })
    }
  
    useEffect(() => {
      createRequest(`${BASE_URL}/${api}/countries`, setCountries, true)
    }, [api])
  
    useEffect(() => {
      // console.log("date", dateVal.toISOString().substring(0, 10))
      if (countryName == "") return
      setData({isLoading: true})
      createRequest(`${BASE_URL}/${api}/countries/${countryName}?day=${startDateVal.toISOString().substring(0, 10)}`, setData)
  
    }, [countryName, startDateVal])
  


    return (
    <>
        <div className='centerContainer'>
            <h1>Covid Data</h1>
        </div>
        {!isFullLoaded 
        ?
            <div className='centerContainer'>
            <CircularProgress />  
            </div>
        :
            <div className='customContainer'>

                <Autocomplete
                options={countries}
                onChange={(_, val) => setCountryName(val)}
                renderInput={(params) => <TextField {...params} label="Country"/>}
                />

                <br />
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                <DatePicker
                    label="Date"
                    inputFormat='dd/MM/yyyy'
                    value={startDateVal}
                    onChange={(newValue) => {
                    setStartDateVal(newValue)
                    }}
                    renderInput={(params) => <TextField {...params} />}
                />
                </LocalizationProvider>
                

                {/* {Object.keys(data).length !== 0 && <DataShown totalData={data}/>} */}
            </div>
        }
    </>
    )
}

export default Inputs