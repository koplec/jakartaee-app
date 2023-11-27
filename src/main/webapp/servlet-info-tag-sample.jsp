<%@ page contentType="text/html;charset=UTF-8" %>
<!-- タグライブラリ -->
<%@ taglib prefix="koptag" uri="https://koplec.prv/MyTagLibs-1.0" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ServletInfoTag Sample</title>
</head>
<body>
    <table>
    <koptag:ServletInfo os.name="true" os.version="true" java.home="true" java.class.path="false">
    <tr>
        <th>${name}</th>
        <td>${value}</td>
    </tr>
    </koptag:ServletInfo>
    </table>
</body>
</html>