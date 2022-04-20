import { CircularProgress } from "@mui/material";
import React from "react";
import { Col, Row } from "react-bootstrap";
import CountryData from "./CountryData";

const DataShown = ({data, isLoading}) => {
    
    return (
        <>
            {isLoading 
            ? 
                <>
                    <br />
                    <div className='centerContainer'>
                        <CircularProgress />  
                    </div>
                </>
            : 
                <>
                    {data.map(country => <CountryData key={country.country} country={country} />)}
                </>
            }
            
            
        </>
    )

}

export default DataShown