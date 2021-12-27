import React,{Component} from "react";

class Modal extends Component{
    render() {
        
        const{handleClose,desc,show,header,footer}=this.props
        const showHideClassName= show ? 'display-block':'display-none'
        
        return (
             <div className={showHideClassName}>
                 <div id="mymodal" className="modal">
                    <div className="modal-content">
                        <div className="modal-header">
                            <span className="close" onClick={handleClose}>X</span>
                            <h2>{header}</h2>
                        </div>
                        <div className="modal-body">
                            <div>{desc}</div>  
                        </div>
                        <div className="modal-footer">
                            <h3>{footer}</h3>
                        </div>
                    </div>
                 </div>
             </div>
        );
    }
}

export default Modal;