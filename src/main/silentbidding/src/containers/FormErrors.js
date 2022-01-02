import React from "react";
/**
 * displays errors that may occur in the forms
 * @param {*} param0 
 * @returns 
 */

export const FormErrors=({formErrors})=>
<div>
    {/**
     * loop through the errors diplaying each one by one
     */
    Object.keys(formErrors).map((fieldName,i)=>{
        if(formErrors[fieldName].length>0){
            return(
                <p key={i} className='error'>{fieldName} {formErrors[fieldName]}</p>
            );
        }else{
            return'';
        }
    })}
</div>