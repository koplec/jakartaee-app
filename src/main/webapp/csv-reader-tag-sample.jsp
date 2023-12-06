<%@ page contentType="text/html;charset=UTF-8" %>
<%-- タグライブラリを有効化 --%>
<%@ taglib prefix="koptag" uri="https://koplec.prv/MyTagLibs-1.0" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CsvReaderTagのSample</title>

</head>
<body>
    <!-- カスタムタグ呼び出し -->
    <koptag:CsvReader path="/WEB-INF/data/data.txt" delimiter="\t" />
</body>
</html>