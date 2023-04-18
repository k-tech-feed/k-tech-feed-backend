import express from "express";
import parseRss from "../controllers/rss.controller/parseRss.js";

const router = express.Router();

router.post("/", parseRss);

export default router;
