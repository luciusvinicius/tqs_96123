import React from "react"

const CountryData = ({country, setWarning, hasWarning}) => {

    return (
        country.country != null 
        ?
            <>
                <h2>Covid Data for {country.date}</h2>
                <p>Country: {country.country}</p>
                <p>Recovered: {country.recovered}</p>
                <h3>Active</h3>
                <p>Population: {country.newActive}</p>
                <p>Total Tests: {country.active}</p>
                <h3>Deaths</h3>
                <p>New Deaths: {country.newDeaths}</p>
                <p>Total Deaths: {country.deaths}</p>
            </>
        : !hasWarning && setWarning(true)
    )
}

export default CountryData