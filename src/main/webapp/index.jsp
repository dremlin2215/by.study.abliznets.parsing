<html>
<body>
<p align="center">Study JAVA parsers</p>
<form action="parse" enctype="multipart/form-data" method="post">
    <p align="center"><b>Choose the parser type</b></p>
    <p align="center">
        <label>
            <select name="parser" size="1">
                <option value="dom">DOM</option>
                <option value="sax">SAX</option>
                <option value="stax">StAX</option>
            </select>
        </label>
    </p>
    <p align="center"><b>Choose the file</b></p>
    <p align="center"><input type="file" name="file"><input type="submit" value="Send"></p>
</form>
</body>
</html>
