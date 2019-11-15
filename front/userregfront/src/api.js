import axios from "axios";
const headers = {
  'Access-Control-Allow-Origin': '*',
};
const api = axios.create({
  baseURL: "http://localhost:8080/v2/",
  headers
});

export default api;
