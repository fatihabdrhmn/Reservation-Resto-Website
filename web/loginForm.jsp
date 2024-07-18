<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!--    link to CSS-->
    <link rel="stylesheet" href="CSS/login.css">
    
    <!-- link to sweetalert2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">

    <!-- box icon -->
    <link rel="stylesheet"
    href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
    
    <title>Login</title>
</head>
<body>

    <header>
        <a href="#" class="Logo"><i class='bx bxs-bowl-hot'></i>Restoku.</a>

        <nav class="navbar">
            <a href="Restoku.html">Home</a>
            <a href="Restoku.html">Dishes</a>
            <a href="Restoku.html">About</a>
            <a href="Restoku.html">Menu</a>
            <a href="Restoku.html">Location</a>
            <a href="Restoku.html">Book</a>            
        </nav>

        <div class="icons">
            <i class="fas fa-bars" id="menu-bars"></i>
           <a href="#" class='bx bx-user'></a>
        </div>
     </header>
     
    <section class="login-section">
        <div class="login-container">
            <div class="login-box">
                <div class="login-image">
                    <img src="image/chef.png" alt="Login Image">
                </div>
                <div class="login-form">
                    <h2 class="heading">Login</h2>
                    <form action="LoginServlet" method="POST">
                        <div class="inputbox">
                            <label for="username">Username</label>
                            <input type="text" id="username" name="username" required>
                        </div>
                        <div class="inputbox">
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
