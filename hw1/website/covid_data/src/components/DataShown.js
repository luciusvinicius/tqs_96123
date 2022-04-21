import { Alert, CircularProgress } from "@mui/material";
import React, { useState } from "react";
import CountryData from "./CountryData";

const DataShown = ({data, isLoading}) => {

    const [showWarning, setShowWarning] = useState(false)
    
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
            data.length == 0 
            ?
                <p>Nothing found with those parameters. Try to change them or try it later.</p>
            :
                <>
                    {data.map((country, idx) => <CountryData 
                        key={country.country + idx} 
                        country={country} 
                        setWarning={setShowWarning} 
                        hasWarning={showWarning}
                    />)}

                    {showWarning && 
                        <Alert severity="warning">
                            Selected external API didn't have content for all these days, some maybe are missing.
                            This happens usually with the second external API in the morning, when they didn't have uploaded new content yet.
                        </Alert>
                    }
                </>
            }

            
            
        </>
    )

}

export default DataShown