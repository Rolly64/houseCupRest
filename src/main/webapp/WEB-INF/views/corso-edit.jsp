<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Modifica corso</title>
</head>
<body>
<form action="/api/course/${corso.id}/edit" method="post">
    <input type="hidden" name="id" value="${corso.id}"/>

    <input type="date" name="end_date" value="${corso.endDate}"/>

    <input type="date" name="start_date" value="${corso.startDate}"/>

    <input type="text" name="class_name" value="${corso.className}"/>
</form>
</body>
</html>