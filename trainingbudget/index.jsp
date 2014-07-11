<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>TrainingBudget</title>
    <script type="text/javascript">
             function checkId(){
             var myId = document.getElementById("id").value;
             var re = /^\d{5}$/;
                 if(!re.test(myId)){
                     alert("the id is wrong, please input again");
                     return false;}
                 return true;}
     </script>
</head>
<body>
<div style="text-align:center;font-size = 20px">
    <h1 style="color:blue">Budget Query</h1>

    <form action="QueryForm" method="post" name="information" id="information" onsubmit="return checkId()">
        <div style="margin:10px">
            <span style="padding:20px">name</span>
            <input type="text" name="name" id="name">
        </div>

        <div style="margin:10px">
            <span style="padding:30px">id</span>
            <input type="text" name="id" id="id">
        </div>
        <input type="button" name="cancel" value="quit" onclick="close();document.write('<n>')" style="margin:0px 30px">
        <input type="submit" name="submit" value="submit" style="margin:0px 20px">


    </form>
</div>
    <%
        Object msg = request.getAttribute("errorStr");
        if(msg!=null){
            msg.toString();
    %>
        <div style="color:blue;text-align:center;font-size = 16px"><%=msg%></div>
    <%
    }
    %>
</body>
</html>