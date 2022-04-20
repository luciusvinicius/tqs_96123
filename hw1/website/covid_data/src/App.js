import './App.css';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { useEffect, useState } from 'react';
import Inputs from './components/Inputs';



function App() {

  const [api, setApi] = useState("api1")

  useEffect(() => console.log("new api", api), [api])


  return (
    <div className="App">
      <FormControl>
        <FormLabel id="demo-row-radio-buttons-group-label">Choose API</FormLabel>
        <RadioGroup
          row
          aria-labelledby="demo-row-radio-buttons-group-label"
          name="row-radio-buttons-group"
          value={api}
          onChange={(e) => setApi(e.target.value)}
        >
          <FormControlLabel value="api1" control={<Radio />} label="API 1" />
          <FormControlLabel value="api2" control={<Radio />} label="API 2" />
        </RadioGroup>
      </FormControl>

      <Inputs
        api={api}
      />

    </div>
  );
}

export default App;
