import express from "express";
import dotenv from "dotenv";
import router from "./routes/index.router.js";

dotenv.config();

const app = express();
const port = process.env.PORT || 3000;

app.use("/", router);

app.listen(port, () => {
  console.log(`Running on ${port}`);
});
