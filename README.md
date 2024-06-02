# Project-Mobile
# Deskripsi Aplikasi
1. Tema           : Education
2. Nama aplikasi  : Student Tracker
   
Student Tracker merupakan aplikasi yang dirancang khusus untuk memudahkan pelacakan dan manajemen informasi mahasiswa yang memiliki matakuliah atau course yang berbeda di negara USA.  Memiliki antarmuka pengguna yang intuitif, Student Tracker menjadi alat yang esensial bagi institusi pendidikan atau dosen untuk mengelola data mahasiswa. Karena di dalam aplikasi Student Tracker terdapat informasi detail mengenai mahasiswa.

# Fitur-fitur Aplikasi
1. Login dan Register : Memungkinkan pengguna untuk membuat akun baru atau masuk ke akun yang sudah terdaftar sebelumnya.
2. Home : menampilkan daftar seluruh mahasiswa dengan ringkasan informasi penting seperti nama, email, GPA, dan kursus yang diambil. 
3. Searching : Mencari profile mahasiswa dengan cepat berdasarkan nama. 
4. Favorite : Menyimpan profile mahasiswa ke Favorite 
5. Profile User : Menampilkan informasi tentang pengguna, termasuk username dan email. 
6. Profile Mahasiswa : Menampilkan informasi lengkap tentang mahasiswa, mulai dari nama, umur, dll.
7. Logout : Memungkinkan pengguna untuk keluar dari akun mereka setelah selesai menggunakan aplikasi.

# Cara Penggunaan Aplikasi
1. Register
   Untuk mulai menggunakan Student Tracker, Anda perlu membuat akun terlebih    dahulu. Jika belum memiliki akun maka pilih button register atau text        daftar, kemudian akan diarahkan kehalaman register. Dihalaman register       Anda wajib mengisi formulir dengan informasi yang diperlukan seperti         username, email, dan password. Setelah selesai mengisi data maka klik        button Register.
2. Login
   Jika sudah memiliki akun, maka anda akan dibawa pada halaman login           (halaman sebelumnya), di halaman ini anda mengisi kembali informasi yang     telah terdaftar sebelumnya saat anda membuat akun. Jika sudah selesai        maka klik button login, nanti anda akan di bawa kehalaman utama yaitu        halaman Home.
3. Home
   Halaman Home adalah tempat pertama yang Anda lihat setelah login. Di         sini, Anda akan menemukan daftar seluruh mahasiswa yang terdaftar di         aplikasi. Informasi yang ditampilkan termasuk nama, email, GPA, dan mata     kuliah yang diambil. Anda dapat menggulir ke bawah untuk melihat lebih       banyak mahasiswa. Selain itu terdapat fitur Searching, anda bisa mencari    nama mahasiswa sesuai yang anda inginkan dengan mudah tanpa menggulir        kebawah.
4. Profile Mahasiswa
   Ketika anda mengklik item mahasiswa di halaman home maka anda akan dibawa    kehalaman profile mahasiswa tersebut. Disini anda dapat melihat informasi    lengkap tentang seorang mahasiswa.
5. Favorite
   Jika Anda ingin menyimpan profil mahasiswa untuk referensi cepat di masa     depan, buka profil mahasiswa yang diinginkan dan klik ikon "Favorite"        (berbentuk love). Untuk mengakses daftar mahasiswa yang telah Anda tandai    sebagai favorit, buka menu dan pilih opsi "Favorite".
7. Profile User
   Untuk melihat informasi pribadi anda seperti username dan email, anda        dapat memilih menu profil di pojok kanan bawah.
8. Logout
   Setelah selesai menggunakan aplikasi, Anda dapat keluar dengan aman.         Dihalaman Profile User terdapat tombol logout, anda dapat mengklik tombol    tersebut jika ingin keluar dari aplikasi.

# Implementasi Teknis
1. Register dan Login\
   Membuat layout untuk halaman register dan login,CardView untuk desain card serta terdapat font untuk mengatur tampilan judul. Tahap berikutnya implementasikan kode di        dalam loginActivity dan registerActivity untuk mengelola proses registrasi dan login. Saat pengguna mengisi formulir registrasi dan mengklik tombol "Register", data yang     dimasukkan akan disimpan menggunakan SharedPreferences. Kita akan menyimpan username dan email pengguna ke dalam SharedPreferences untuk digunakan di halaman profil.
2. Home
   HomeFragment bertanggung jawab untuk menampilkan daftar mahasiswa dalam bentuk RecyclerView, menyediakan SearchView untuk pencarian mahasiswa berdasarkan nama, terdapat Handler untuk mengatur progresBar dan menyertakan Toolbar untuk navigasi tambahan.
3. Profile Mahasiswa
   Pada ProfileActivity, instance tunggal dari UserDatabase diperoleh menggunakan getInstance untuk memastikan hanya ada satu instance database yang digunakan di seluruh aplikasi. UserDatabase dikonfigurasi dengan Room dan memiliki metode untuk mengambil pengguna saat ini. Entitas User mewakili tabel dalam database, profileActivity menampilkan informasi detail profil mahasiswa yang diambil dari database.
4. Favorite
   Menggunakan SQLite untuk menyimpan data yang dimasukkan ke dalam daftar favorite. Data yang disimpan dalam tabel favorite yaitu nama, email, gpa dan course.
   Saat fragment dibuat (onCreateView), DatabaseHelper diinisialisasi dan digunakan untuk mengambil data favorit pengguna dari database. Data favorit dimuat dalam thread terpisah dan setelah data diambil, RecyclerView diperbarui untuk menampilkan daftar mahasiswa favorit.
5. Profile User
   Pada profile user, memanfaatkan SharedPreferences untuk menyimpan dan mengambil data pengguna, seperti username dan email, serta untuk mengelola status login pengguna. Saat fragment dibuat (onCreateView), data pengguna diambil dari SharedPreferences dan ditampilkan setelah penundaan singkat menggunakan Handler untuk memberikan efek loading. Tombol logout menghapus status login dari SharedPreferences dan mengarahkan pengguna kembali ke LoginActivity.

# Teknologi Yang Digunakan
1. Android Studio
2. Retrofit
3. SharedPreferences
4. SQLite
5. CardView
6. Fragment & Navigation
7. Background Thread (Handler)

