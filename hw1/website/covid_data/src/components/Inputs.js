import React from "react"
import { Alert, Autocomplete, CircularProgress, TextField } from '@mui/material';
import { useEffect, useState } from 'react';
import DataShown from './DataShown';

import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

const Inputs = ({api}) => {

    const BASE_URL = "http://localhost:8080"

    const [countries, setCountries] = useState([])
    const [countryName, setCountryName] = useState("")
    const [data, setData] = useState([])
    const [isFullLoaded, setIsFullLoaded] = useState(false)
    const [startDateVal, setStartDateVal] = useState(new Date())
    const [endDateVal, setEndDateVal] = useState(new Date())
    const [countriesIsLoading, setCountriesIsLoading] = useState(true)

  
    const createApiRequest = (uri, setFunc) => {

        setIsFullLoaded(false)

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
            // console.log("response", response)
            if (api == "api1") {
                setFunc(response.response)
            }
            else {
                setFunc(response.data.map(c => c.name))
            }

            setIsFullLoaded(true)
        })
        .catch(error => {
            console.log("sussy error", error)
        })
    }

    const createCountryRequest = (uri, setFunc) => {

        fetch(uri, {
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        })
        
        .then(response => {
            // console.log("first response", response)
            return response.json()
        } )
        .then(response => {
            // console.log(`response for ${uri}`, response)
            setFunc(response)
            setCountriesIsLoading(false)

        })
        .catch(error => {
            console.log("sussy error", error)
        })
    }
  
    useEffect(() => {
      createApiRequest(`${BASE_URL}/${api}/countries`, setCountries)
    }, [api])
  
    useEffect(() => {
      // console.log("date", dateVal.toISOString().substring(0, 10))
      if (countryName == "") return
      setCountriesIsLoading(true)
      createCountryRequest(`${BASE_URL}/${api}/countries/${countryName}?startDay=${startDateVal.toISOString().substring(0, 10)}&endDay=${endDateVal.toISOString().substring(0, 10)}`, setData)
  
    }, [countryName, startDateVal])
  


    return (
    <>
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
                        label="Start Date"
                        inputFormat='dd/MM/yyyy'
                        value={startDateVal}
                        onChange={(newValue) => {
                        setStartDateVal(newValue)
                        }}
                        renderInput={(params) => <TextField {...params} />}
                        className="textInput"
                    />
                </LocalizationProvider>
                <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DatePicker
                        label="End Date"
                        inputFormat='dd/MM/yyyy'
                        value={endDateVal}
                        onChange={(newValue) => {
                        setEndDateVal(newValue)
                        }}
                        renderInput={(params) => <TextField {...params} />}
                                            
                    />
                </LocalizationProvider>
                <br/>
                <br/>

                <Alert severity="warning">Doing a request for a good amount of days may take a while to have a response!</Alert>

                

                {data.length != 0 && <DataShown data={data} isLoading={countriesIsLoading}/>}
            </div>
        }
    </>
    )
}

export default Inputs