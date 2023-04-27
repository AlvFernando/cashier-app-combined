import { Button, Paper, TextField, Typography } from "@mui/material";
import axios from "axios";
import React from "react";
import { useState } from "react";
import { linkActivateProduct } from "../service/linkApi";
import { useNavigate } from "react-router-dom";
import Spinner from "../components/Spinner";

const Activation = () => {
  const navigate = useNavigate();

  const [inputUser, setInputUser] = useState("");
  const [loading, setLoading] = useState(false);

  const valid = inputUser.length > 0;

  const handleActivate = () => {
    setLoading(true);
    axios
      .post(linkActivateProduct, {
        productKey: inputUser,
      })
      .then((res) => {
        console.log("success post activation key", res);
        navigate(0);
      })
      .catch((err) => console.log("failed post activation key", err));
  };

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
        <Spinner loading={loading} />
        <div className='flex-row' style={{ justifyContent: "center" }}>
          <Button
            disabled={!valid}
            sx={{ width: "80%" }}
            variant='contained'
            onClick={handleActivate}>
            SUBMIT
          </Button>
        </div>
      </Paper>
    </div>
  );
};

export default Activation;
