# Instruksi Penggunaan Program

Program ini menggunakan Hibernate sebagai ORM dan PostgreSQL sebagai database.
Sebelum menjalankan program, Anda harus melakukan konfigurasi sebagai berikut:

## 1. Mengubah Nama File Konfigurasi
Di dalam folder resources, terdapat file `hibernate.example.properties`. Ubah nama file tersebut menjadi:

```
hibernate.properties
```

## 2. Mengedit Konfigurasi Database
Buka file `hibernate.properties` dan ubah nilai berikut sesuai dengan database yang Anda gunakan:

```
hibernate.connection.driver_class=org.postgresql.Driver
hibernate.connection.url=jdbc:postgresql://localhost:5432/{database_name}  # Ganti {database_name} dengan nama database Anda
hibernate.connection.username=${username}  # Ganti ${username} dengan username PostgreSQL Anda
hibernate.connection.password=${password}  # Ganti ${password} dengan password PostgreSQL Anda
```

## 3. Menjalankan Program
Setelah mengonfigurasi file `hibernate.properties`, Anda dapat menjalankan program seperti biasa.
Pastikan PostgreSQL sudah berjalan dan dapat diakses oleh aplikasi ini.
