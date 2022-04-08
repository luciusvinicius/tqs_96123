import logo from './logo.svg';
import './App.css';
import { Autocomplete, TextField } from '@mui/material';
import { useEffect, useState } from 'react';


const testOptions = ["Among", "us"]

const apiURL = "http://localhost:8080"


function App() {

  const [countries, setCountries] = useState([])

  useEffect(() => {
    fetch(`${apiURL}/countries`, {
      headers:{
          Accept: 'application/json',
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*'
          // // "Origin":"frontend:3000"
      }
    })

    .then(response => response.json())
    .then(response => console.log(response))

  }, [])

  return (
    <div className="App">
      <div className='customContainer'>
        <Autocomplete
            options={testOptions}
            renderInput={(params) => <TextField {...params} margin="normal" label="Movie" />}
          />
      </div>
    </div>
  );
}

export default App;
