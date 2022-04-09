import logo from './logo.svg';
import './App.css';
import { Autocomplete, CircularProgress, TextField } from '@mui/material';
import { useEffect, useState } from 'react';
import DataShown from './DataShown';


const testOptions = ["Among", "us"]

const apiURL = "http://localhost:8080"


function App() {

  const [countries, setCountries] = useState([])
  const [countryName, setCountryName] = useState("")
  const [data, setData] = useState({})
  const [isFullLoaded, setIsFullLoaded] = useState(false)

  const createRequest = (uri, setFunc, changeLoad=false) => {
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
      setFunc(response.response)
      if (changeLoad) {
        setIsFullLoaded(true)
      }
    })
    .catch(error => {
      console.log("sussy error", error)
    })
  }


  useEffect(() => {
    createRequest(`${apiURL}/countries`, setCountries, true)
  }, [])

  useEffect(() => {
    if (countryName == "") return
    setData({isLoading: true})
    createRequest(`${apiURL}/countries/${countryName}`, setData)

  }, [countryName])

  return (
    <div className="App">
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
        {Object.keys(data).length !== 0 && <DataShown totalData={data}/>}
      </div>
      }
    </div>
  );
}

export default App;
