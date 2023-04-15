import { Delete, Remove } from "@mui/icons-material";
import {
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Paper,
  Select,
  TextField,
} from "@mui/material";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { linkApi } from "../service/linkApi";

const PrintSettings = () => {
  const [inputUser, setInputUser] = useState({});
  const [dataPrinterApi, setDataPrinterApi] = useState([]);
  const [trigger, setTrigger] = useState(false);

  useEffect(() => {
    axios
      .get(`${linkApi}api/getprinterdevice`)
      .then((res) => {
        setDataPrinterApi(res.data.data);
        console.log("success get data all printer", res.data.data);
      })
      .catch((err) => console.log("error get data all printer", err));

    axios
      .get(`${linkApi}api/usedprinterdevice`)
      .then((res) => {
        setInputUser({
          ...inputPrinter,
          [inputPrinter.printerUsed]: res.data.data.printerName,
        });
        console.log("success get data printer used", res);
      })
      .catch((err) => console.log("error get data printer used", err));
  }, [trigger]);

  const handleTrigger = (childData) => {
    setTrigger(childData);
  };

  const reset = () => {
    setInputUser({});
  };

  const handleInputChange = (e) => {
    setInputUser((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleNewPrinter = () => {
    axios
      .put(`${linkApi}api/usedprinterdevice`, {
        printerName: inputUser.newPrinter,
      })
      .then((res) => {
        console.log("success put new printer", res);
        setTrigger(!trigger);
      })
      .catch((err) => console.log("error put new printer", err));
  };

  const handleEditUsedPrinter = () => {
    axios
      .put(`${linkApi}api/usedprinterdevice`, {
        printerName: inputUser.printerUsed,
      })
      .then((res) => {
        console.log("success put used printer", res);
        setTrigger(!trigger);
      })
      .catch((err) => console.log("error put used printer", err));
  };

  // const dataPrinter = [
  //   { id: 1, printerName: "Epson" },
  //   { id: 2, printerName: "Hp" },
  // ];

  return (
    <div>
      <div className='flex-row' style={{ justifyContent: "stretch" }}>
        <Paper sx={{ p: 2, m: 1, flex: 1, alignSelf: "stretch" }}>
          <div>Choose Your Printer</div>
          <FormControl sx={{ width: "200px", my: 2 }} size='small'>
            <InputLabel id='demo-select-small'>Printer List</InputLabel>
            <Select
              labelId='demo-select-small'
              id='demo-select-small'
              name='printerUsed'
              value={inputUser.printerUsed}
              label='Choose Printer'
              onChange={handleInputChange}>
              {dataPrinterApi.map((e, index) => (
                <MenuItem key={index} value={e.printerName}>
                  {e.printerName}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <div style={{ marginBottom: "10px" }}>
            <Button variant='contained' onClick={handleEditUsedPrinter}>
              Choose Printer
            </Button>
          </div>
          <div>Add New Printer</div>
          <TextField
            name='newPrinter'
            value={inputUser.newPrinter || ""}
            onChange={handleInputChange}
            variant='outlined'
            placeholder='insert New Printer'
            fullWidth
            sx={{ mt: 2, mb: 2 }}
          />
          <Button variant='contained' onClick={handleNewPrinter}>
            Add New
          </Button>
        </Paper>
      </div>
    </div>
  );
};

export default PrintSettings;
