const parseRss = (req, res) => {
  console.log("parseRSS");

  res.status(200).json({ message: "success to parse" });
};

export default parseRss;
