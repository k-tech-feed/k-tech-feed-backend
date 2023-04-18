import createError from "http-errors";
import express from "express";
import dotenv from "dotenv";
import router from "./routes/index.router.js";
import cors from "cors";
import morganMiddleware from "./middlewares/morganMiddlewares.js";
import cron from "node-cron";

dotenv.config();

const app = express();
const port = process.env.PORT || 3000;

app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(
  cors({
    origin: "*",
  })
);

app.use("/", router);

app.use(morganMiddleware);
app.use((req, res, next) => {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get("env") === "development" ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.json({ message: `There is an Error...${err.message}` });
});

app.listen(port, () => {
  console.log(`Running on ${port}`);
  console.log("Schedule cron job (every 23 hours)");
  cron.schedule("* * */23 * *", () => {
    console.log("run every 23 hours");
  });
});
