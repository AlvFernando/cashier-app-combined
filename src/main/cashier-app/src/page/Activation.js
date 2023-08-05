import { Button, Paper, TextField, Typography } from "@mui/material";
import axios from "axios";
import React, { useEffect } from "react";
import { useState } from "react";
import { linkActivateProduct, linkApi } from "../service/linkApi";
import { useNavigate } from "react-router-dom";
import Spinner from "../components/Spinner";

const Activation = () => {
  const navigate = useNavigate();

  const [inputUser, setInputUser] = useState("");
  const [loading, setLoading] = useState(false);
  const [productKey, setProductKey] = useState("");

  const valid = inputUser.length > 0;

  useEffect(() => {
    axios.get(`${linkApi}productkey/getproductid`).
      then((res) => {
        // console.log("response getproductid: ", res);
        if (res.data.data.isActive === true) {
          setProductKey(res.data.data.productKey);
        } else {
          alert("Product key invalid");
        }
      }).
      catch((err) => console.log("error getproductid", err));
  }, [])

  const handleActivate = () => {
    setLoading(true);
    axios
      .post(linkActivateProduct, {
        productKey: inputUser,
      })
      .then((res) => {
        // console.log("success post activation key", res);
        navigate(0);
      })
      .catch((err) => console.log("failed post activation key", err));
  };

  const handleCloseApp = () => {
    axios
      .get(`${linkApi}exit`)
      .then((res) => console.log("response /exit", res))
      .catch((err) => alert(`error exit app with message: ${err}`))
  }

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
          {
            productKey.length > 0
              ?
              <>
                YOUR PRODUCT HAS BEEN ACTIVATED
              </>
              :
              <>
                ACTIVATE YOUR PRODUCT
              </>
          }
        </Typography>
        <div
          className='flex-row'
          style={{ justifyContent: "center", marginBottom: "30px" }}>
          {
            productKey.length > 0
              ?
              <>
                <Typography variant="h7" sx={{ width: "80%", textAlign: "center" }}>
                  {productKey}
                </Typography>
              </>
              :
              <>
                <TextField
                  id='outlined-basic'
                  label='please input your key here'
                  variant='outlined'
                  sx={{ width: "80%" }}
                  value={inputUser}
                  onChange={(e) => setInputUser(e.target.value)}
                />
              </>
          }
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
        {
          productKey.length <= 0 &&
          <div className='flex-row' style={{ justifyContent: "center", marginTop: "20px" }}>
            <Button
              sx={{ width: "80%" }}
              variant='contained'
              onClick={handleCloseApp}>
              CLOSE APPLICATION
            </Button>
          </div>
        }
      </Paper>
    </div>
  );
};

export default Activation;
