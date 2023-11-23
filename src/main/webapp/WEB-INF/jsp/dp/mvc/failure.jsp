<%@page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="mailingbean" scope="request" class="prv.koplec.dp.mvc.MailingBeanImpl" />
<!-- MVC VIEW -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登録結果</title>
</head>
<body>
    <jsp:getProperty name="mailingbean" property="last" />&nbsp;&nbsp;
    <jsp:getProperty name="mailingbean" property="first" />様
    <br><br>
    申し訳ありませんが、以下のアドレス
    <jsp:getProperty name="mailingbean" property="email" />
    はリストに登録できませんでした。
    エラーの原因は以下の通りです。
    <jsp:getProperty name="mailingbean" property="errorString" />
</body>
</html>