import React, { useState } from "react";
import SideNav, { NavItem, NavIcon, NavText } from "@trendmicro/react-sidenav";
import ClickOutside from "./ClickOutside";
import {
  Add,
  Edit,
  History,
  Home,
  Key,
  Print,
  Remove,
  Settings,
} from "@mui/icons-material";

// Be sure to include styles at some point, probably during your bootstraping
import "@trendmicro/react-sidenav/dist/react-sidenav.css";
import { useNavigate } from "react-router-dom";

const Sidenav = ({ childToParent }) => {
  const [expand, setExpand] = useState(false);
  const navigate = useNavigate();

  return (
    <div>
      <ClickOutside
        onClickOutside={() => {
          // childToParent(expand)
          // setExpand(false);
        }}>
        <SideNav
          className='sidenav'
          onSelect={(selected) => {
            console.log("selected", selected);
            navigate(selected);
          }}
          expanded={expand}
          onToggle={() => {
            childToParent(!expand);
            setExpand(!expand);
          }}>
          <SideNav.Toggle />
          <SideNav.Nav defaultSelected='home'>
            <NavItem eventKey='home'>
              <NavIcon>
                <Home sx={{marginTop: "12px"}}/>
              </NavIcon>
              <NavText>Home</NavText>
            </NavItem>
            <NavItem eventKey='history'>
              <NavIcon>
                <History sx={{marginTop: "12px"}}/>
              </NavIcon>
              <NavText>History Transaction</NavText>
            </NavItem>
            <NavItem eventKey='settings'>
              <NavIcon>
                <Settings sx={{marginTop: "12px"}}/>
              </NavIcon>
              <NavText>Settings</NavText>
            </NavItem>
            <NavItem eventKey='print-settings'>
              <NavIcon>
                <Print sx={{marginTop: "12px"}}/>
              </NavIcon>
              <NavText>Printer Settings</NavText>
            </NavItem>
            <NavItem eventKey='activation'>
              <NavIcon>
                <Key sx={{marginTop: "12px"}}/>
              </NavIcon>
              <NavText>Activate Product</NavText>
            </NavItem>
          </SideNav.Nav>
        </SideNav>
      </ClickOutside>
    </div>
  );
};

export default Sidenav;
