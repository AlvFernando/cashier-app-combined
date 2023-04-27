import React from "react";
import ClipLoader from "react-spinners/ClipLoader";

const Spinner = ({ loading }) => {
  const override = {
    display: "block",
    margin: "20px auto",
    background: "transparent",
  };

  return (
    <>
      <ClipLoader
        color='darkblue'
        loading={loading}
        cssOverride={override}
        size={50}
        aria-label='Loading Spinner'
        data-testid='loader'
      />
    </>
  );
};

export default Spinner;
