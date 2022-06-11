<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/ClinicManagement/clinic/css/login1.css">
<title>Login</title>
</head>
<body>
  <body>
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h3>LOGIN</h3>
            <p>Please enter your credentials to login.</p>
          </div>
        </div>
        <form class="login-form" action="/ClinicManagement/Login" method="post">
          <input type="text" name="email" placeholder="username"/>
          <input type="password" name="password" placeholder="password"/>
          <button>login</button>
          <div class="text-danger text-center">
               <p> ${message}</p>
           </div>
          <p class="message">Not registered? <a href="#">Create an account</a></p>
        </form>
      </div>
    </div>
</body>
</html>