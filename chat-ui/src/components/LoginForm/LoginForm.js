import { Button, TextField } from "@mui/material";
import React from 'react';
import { useState } from "react";

const LoginForm = ({ onSubmit }) => {

    const [username, setUsername] = useState("");
    let handleUserNameChange = event => setUsername(event.target.value);

    let handleSubmit = () => {
        onSubmit(username);
    }

    return (
        <div>
            <TextField
                label="Type your username"
                placeholder="Username"
                onChange={handleUserNameChange}
                margin="normal"
                onKeyPress={event => {
                    if (event.key === 'Enter') {
                        handleSubmit();
                    }
                }}
            />
            <br />
            <Button variant="contained" color="primary" onClick={handleSubmit} >
                Login
             </Button>

        </div>
    )
}
export default LoginForm