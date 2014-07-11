 
<html>
    <body>
        <h1 style = "color:blue;text-align:center;font-size = 19px">Query Result</h1>
        <div style="background-color:#EEEEEE;width:'100%';color:blue;font-size:16px;">
            <div style="float:left;width:28%; margin:20px 30px" >ID</div>
            <div style="float:left;width:28%; margin:20px 0px">NAME</div>
            <div style="float:left;width:28%; margin:20px 0px">BLANCE</div>
        </div>

        <%
            String name = request.getAttribute("name").toString();
            String id = request.getAttribute("id").toString();
            String balance = request.getAttribute("left").toString();

        %>

           <div style="background-color:#EEEEEE;width:'100%';font-size:14px;">
                 <div style="float:left;width:28%; margin:10px 30px" ><%=id%></div>
                 <div style="float:left;width:28%; margin:10px 0px"><%=name%></div>
                 <div style="float:left;width:28%; margin:10px 0px"><%=balance%></div>
           </div>

    </body>
</html>