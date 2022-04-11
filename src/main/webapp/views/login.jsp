<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <link rel="stylesheet" href="/css/style.css" />
    </head>

    <body>
        <div class="py-20">
            <div class="flex bg-white rounded-lg shadow-lg overflow-hidden mx-auto max-w-sm lg:max-w-4xl">
                <div class="hidden lg:block lg:w-1/2 bg-cover"
                    style="background-image:url('https://media.istockphoto.com/photos/little-kids-schoolchildren-pupils-students-running-hurrying-to-the-picture-id1338737959?b=1&k=20&m=1338737959&s=170667a&w=0&h=Wj4IEck0-UYQquyOfnORvotapuarDJ4fABPDm-9ITV0=')">
                </div>
                <div class="w-full p-8 lg:w-1/2">
                    <h2 class="text-2xl font-semibold text-gray-700 text-center">CNPM School</h2>

                    <div class="mt-4 flex items-center justify-between">
                        <span class="border-b w-1/5 lg:w-1/4"></span>
                        <a href="#" class="text-xs text-center text-gray-500 uppercase">Login to system</a>
                        <span class="border-b w-1/5 lg:w-1/4"></span>
                    </div>
                    <form method="POST" id="form-login">
                        <div class="mt-4">
                            <label class="block text-gray-700 text-sm font-bold mb-2">UserName</label>
                            <input
                                class="bg-gray-200 text-gray-700 focus:outline-none focus:shadow-outline border border-gray-300 rounded py-2 px-4 block w-full appearance-none"
                                id="username" type="text" name="username" required>
                        </div>
                        <div class="mt-4">
                            <div class="flex justify-between">
                                <label class="block text-gray-700 text-sm font-bold mb-2">PassWord</label>
                            </div>
                            <input
                                class="bg-gray-200 text-gray-700 focus:outline-none focus:shadow-outline border border-gray-300 rounded py-2 px-4 block w-full appearance-none"
                                type="password" id="password" name="password" required>
                        </div>
                        <div class="mt-8">
                            <button class="bg-gray-700 text-white font-bold py-2 px-4 w-full rounded hover:bg-gray-600"
                                type="submit">Login</button>
                        </div>
                    </form>
                    <div class="flex my-3 bg-red-100 rounded-md ${class_error} mb-4 text-sm text-red-700" role="alert">
                        <div>
                            <span class="font-medium">${error}</span>
                        </div>
                    </div>


                    <div class="mt-4 flex items-center justify-between">
                        <span class="border-b w-1/5 md:w-1/4"></span>
                        <a href="#" class="text-xs text-gray-500 uppercase">Private website</a>
                        <span class="border-b w-1/5 md:w-1/4"></span>
                    </div>
                </div>
            </div>
        </div>
        <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
            integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script>
            let formLogin = document.getElementById('form-login')
            let username = document.getElementById('username')
            let password = document.getElementById('password')
            formLogin.addEventListener("submit", e => {
                e.preventDefault()
                $.ajax({
                    url: `/login`,
                    type: `POST`,
                    data: { username: username.value, password: password.value },
                    success: response => {
                        console.log(response)
                    }
                })
            })
        </script> -->
    </body>

    </html>