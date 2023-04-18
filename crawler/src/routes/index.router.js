import express from "express";
import rssRouter from "./rss.router.js";

const router = express.Router();

router.use("/rss", rssRouter);

export default router;
