import { CircularProgress } from "@mui/material";
import React from "react";
import { Col, Row } from "react-bootstrap";

const DataShown = ({totalData}) => {
    
    const data = totalData[totalData.length - 1]
    console.log("totalData", totalData)
    console.log("data", data)
    return (
        <>
            {totalData.isLoading 
            ? 
                <>
                    <br />
                    <div className='centerContainer'>
                        <CircularProgress />  
                    </div>
                </>
            : 
                <>
                    <h2>General Data</h2>
                    <p>Continent: {data.continent}</p>
                    <p>Country: {data.country}</p>
                    <p>Day: {data.day}</p>
                    <p>Population: {data.population}</p>
                    <p>Total Tests: {data.tests.total}</p>

                    <h2>Deaths</h2>
                    <p>New Deaths: {data.deaths.new == null ? 0 : data.deaths.new}</p>
                    <p>Total Deaths: {data.deaths.total}</p>
                </>
            }
            
            
        </>
    )

}

export default DataShown