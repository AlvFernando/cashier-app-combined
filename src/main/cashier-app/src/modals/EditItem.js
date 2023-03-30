// import React, { useState } from "react";
// import Backdrop from "@mui/material/Backdrop";
// import Box from "@mui/material/Box";
// import Modal from "@mui/material/Modal";
// import Fade from "@mui/material/Fade";
// import Button from "@mui/material/Button";
// import {
//   FormControl,
//   InputLabel,
//   MenuItem,
//   Paper,
//   Select,
//   TextField,
// } from "@mui/material";
// import axios from "axios";
// import { linkApi } from "../service/linkApi";

// const EditItem = ({ openFromParent, dataToModalEdit }) => {
//   const [open, setOpen] = useState(false);
//   const handleOpen = () => setOpen(true);
//   const handleClose = () => setOpen(false);
//   const [inputItemName, setInputItemName] = useState(dataToModalEdit.itemName);
//   const [inputItemPrice, setInputItemPrice] = useState(
//     dataToModalEdit.itemPrice
//   );
//   const [inputItemQuantity, setInputItemQuantity] = useState("");
//   const [inputItemInfo, setInputItemInfo] = useState("");
//   const [dataFromApi, setDataFromApi] = useState([]);

//   const handleEditItem = (e) => {
//     e.preventDefault();
//     axios
//       .post(`${linkApi}api/item`, {
//         itemName: inputItemName,
//         itemPrice: inputItemPrice,
//         itemQty: inputItemQuantity,
//         unitTypeId: satuan,
//       })
//       .then((res) => {
//         console.log("Success Post New Item", res);
//         handleClose();
//       })
//       .catch((err) => console.log("Error Post New Item", err));
//   };

//   const style = {
//     position: "absolute",
//     top: "50%",
//     left: "50%",
//     transform: "translate(-50%, -50%)",
//     width: 500,
//     bgcolor: "background.paper",
//     border: "none",
//     boxShadow: "none",
//     borderRadius: 5,
//     p: 4,
//   };

//   const [satuan, setSatuan] = useState("");

//   const handleChange = (event) => {
//     setSatuan(event.target.value);
//   };

//   return (
//     <>
//     <Button>BENERIN BUTTON EDIT ITEM DISINI</Button>
//     <Modal
//       aria-labelledby='transition-modal-title'
//       aria-describedby='transition-modal-description'
//       open={open}
//       onClose={handleClose}
//       closeAfterTransition
//       slots={{ backdrop: Backdrop }}
//       slotProps={{
//         backdrop: {
//           timeout: 500,
//         },
//       }}>
//       <Fade in={open}>
//         <Box sx={style}>
//           <div style={{ fontSize: "20px" }}>Edit Item</div>
//           <Paper sx={{ mt: 2 }}>
//             <div className='flex-row-sp-between' style={{ margin: "10px" }}>
//               <div>Nama Barang</div>
//               <TextField
//                 value={inputItemName}
//                 onChange={(e) => setInputItemName(e.target.value)}
//                 size='small'
//               />
//             </div>
//             <div className='flex-row-sp-between' style={{ margin: "10px" }}>
//               <div>Harga Barang</div>
//               <TextField
//                 value={inputItemPrice}
//                 onChange={(e) => setInputItemPrice(e.target.value)}
//                 size='small'
//               />
//             </div>
//             <div className='flex-row-sp-between' style={{ margin: "10px" }}>
//               <div>Satuan Barang</div>
//               <FormControl sx={{ width: "200px" }} size='small'>
//                 <InputLabel id='demo-select-small'>Satuan</InputLabel>
//                 <Select
//                   labelId='demo-select-small'
//                   id='demo-select-small'
//                   value={satuan}
//                   defaultValue=''
//                   label='Satuan'
//                   onChange={handleChange}>
//                   {dataFromApi.map((e) => (
//                     <MenuItem key={e.id} value={e.id}>
//                       {e.unitType}
//                     </MenuItem>
//                   ))}
//                 </Select>
//               </FormControl>
//             </div>
//             <div className='flex-row-sp-between' style={{ margin: "10px" }}>
//               <div>Kuantitas</div>
//               <TextField
//                 value={inputItemQuantity}
//                 onChange={(e) => setInputItemQuantity(e.target.value)}
//                 size='small'
//               />
//             </div>
//             {/* <div className='flex-row-sp-between' style={{ margin: "10px" }}>
//                 <div>Keterangan</div>
//                 <TextField
//                   value={inputItemInfo}
//                   onChange={(e) => setInputItemInfo(e.target.value)}
//                   size='small'
//                 />
//               </div> */}
//             <div className='flex-row' style={{ justifyContent: "right" }}>
//               <Button
//                 variant='contained'
//                 sx={{ m: 1 }}
//                 onClick={handleEditItem}>
//                 Add
//               </Button>
//               <Button sx={{ m: 1 }} onClick={handleClose}>
//                 Cancel
//               </Button>
//               <Button variant="contained" sx={{ m: 1 }} onClick={handleClose}>
//                 Delete Item
//               </Button>
//             </div>
//           </Paper>
//         </Box>
//       </Fade>
//     </Modal>
//     </>
//   );
// };

// export default EditItem;
