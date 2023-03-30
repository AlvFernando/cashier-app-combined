import { Button, Paper, TextField, Typography } from "@mui/material";
import React from "react";
import { useState } from "react";

const Activation = () => {
  const [inputUser, setInputUser] = useState("");

  const valid = inputUser.length > 0;

  return (
    <div
      className='flex-row'
      style={{
        justifyContent: "center",
        height: "100vh",
      }}>
      <Paper
        sx={{
          p: 3,
          width: "90%",
          maxWidth: "400px",
          height: "300px",
          borderRadius: "20px",
        }}>
        {" "}
        <Typography
          variant='h5'
          sx={{ mb: 7 }}
          className='flex-row'
          style={{ justifyContent: "center" }}>
          ACTIVATE YOUR PRODUCT
        </Typography>
        <div
          className='flex-row'
          style={{ justifyContent: "center", marginBottom: "30px" }}>
          {/* <Typography>KEY :</Typography> */}
          <TextField
            id='outlined-basic'
            label='please input your key here'
            variant='outlined'
            sx={{ width: "80%" }}
            value={inputUser}
            onChange={(e) => setInputUser(e.target.value)}
          />
        </div>
        <div className='flex-row' style={{ justifyContent: "center" }}>
          <Button disabled={!valid} sx={{ width: "80%" }} variant='contained'>
            SUBMIT
          </Button>
        </div>
      </Paper>
    </div>
  );
};

export default Activation;
