import axios from "axios";
import { linkCheckProductKey } from "./linkApi";

const instance = axios.create();

// Add a request interceptor
instance.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    console.log("masuk interceptors");
    axios
      .get(linkCheckProductKey)
      .then((res) => console.log("response firebase: ", res))
      .catch((err) => {
        console.log("invalid product key", err);
        window.location.href = "/activation";
      });
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

// Add a response interceptor
instance.interceptors.response.use(
  function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  },
  function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    // console.log("invalid product key", error);
    // window.location.href("/activation");
    return Promise.reject(error);
  }
);

export default instance;
