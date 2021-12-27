import React from "react";

export const FormErrors=({formErrors})=>
<div>
    {Object.keys(formErrors).map((fieldName,i)=>{
        if(formErrors[fieldName].length>0){
            return(
                <p key={i} className='error'>{fieldName} {formErrors[fieldName]}</p>
            );
        }else{
            return'';
        }
    })}
</div>