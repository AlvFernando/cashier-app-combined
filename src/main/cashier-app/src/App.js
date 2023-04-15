import React, { useEffect, useState } from "react";
import { Routes, Route, Navigate, useNavigate } from "react-router-dom";
import Home from "./page/Home";
import Sidenav from "./components/Sidenav";
import TransactionHistory from "./page/TransactionHistory";
import Settings from "./page/Settings";
import Activation from "./page/Activation";
import axios from "axios";
import { linkCheckProductKey } from "./service/linkApi";
import PrintSettings from "./page/PrintSettings";

function App() {
  const [expand, setExpand] = useState(false);
  const [margin, setMargin] = useState(64);
  const [valid, setValid] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(linkCheckProductKey)
      .then((res) => {
        console.log("ini response link check product key", res);
        if (res.data.data.isActive === true) {
          setValid(true);
          navigate("/home");
        } else {
          setValid(false);
          navigate("/activation");
          // window.location.href = "/activation"
        }
      })
      .catch((err) => console.log("solution error", err));
  }, []);

  const childToParent = (childData) => {
    setExpand(childData);
    marginSettings();
  };

  const marginSettings = () => {
    if (expand) {
      setMargin(64);
    } else {
      setMargin(240);
    }
  };

  return (
    <>
      <Sidenav childToParent={childToParent}></Sidenav>
      <div style={{ marginLeft: `${margin}px` }}>
        <div>
          {valid === true ? (
            <Routes>
              <Route path='/' element={<Navigate replace to='/home' />} />
              <Route path='/home' element={<Home />}></Route>
              <Route path='/settings' element={<Settings />}></Route>
              <Route path='/print-settings' element={<PrintSettings />}></Route>
              <Route path='/history' element={<TransactionHistory />}></Route>
              <Route path='/activation' element={<Activation />}></Route>
            </Routes>
          ) : (
            <Routes>
              <Route path='/' element={<Navigate replace to='/activation' />} />
              <Route path='/activation' element={<Activation />}></Route>
            </Routes>
          )}
        </div>
      </div>
    </>
  );
}

export default App;
