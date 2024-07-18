<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Success</title>
    <!-- Sisipkan CSS SweetAlert2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <!-- Tambahkan gaya kustom untuk SweetAlert2 -->
    <style>
        .swal2-popup {
            font-family: 'Arial', sans-serif; /* Ganti dengan font yang Anda inginkan */
            font-size: 16px; /* Atur ukuran font sesuai kebutuhan */
        }
        .swal2-title {
            font-family: 'Arial', sans-serif; /* Ganti dengan font yang Anda inginkan */
            font-size: 24px; /* Atur ukuran font sesuai kebutuhan */
        }
        .swal2-content {
            font-family: 'Arial', sans-serif; /* Ganti dengan font yang Anda inginkan */
            font-size: 18px; /* Atur ukuran font sesuai kebutuhan */
        }
        .swal2-confirm {
            font-family: 'Arial', sans-serif; /* Ganti dengan font yang Anda inginkan */
            font-size: 16px; /* Atur ukuran font sesuai kebutuhan */
        }
    </style>
</head>
<body>
    <%@include file="Restoku.jsp" %>
    <!-- Sisipkan SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Reservation successfully submitted! For more info, please contact +62 1188-9763-0091.',
            confirmButtonText: 'OK'
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirect to a different page if needed
                window.location.href = 'Restoku.jsp'; // Ganti dengan halaman tujuan setelah sukses
            }
        });
    </script>
</body>
</html>
