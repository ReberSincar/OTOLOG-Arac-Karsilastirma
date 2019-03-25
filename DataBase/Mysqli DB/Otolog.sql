-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: pdb33.awardspace.net
-- Üretim Zamanı: 10 Ara 2018, 08:58:34
-- Sunucu sürümü: 5.7.20-log
-- PHP Sürümü: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `2891572_otolog`
--
CREATE DATABASE IF NOT EXISTS `2891572_otolog` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `2891572_otolog`;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Kullanicilar`
--

CREATE TABLE `Kullanicilar` (
  `id` int(11) UNSIGNED NOT NULL,
  `admin` bit(1) NOT NULL DEFAULT b'0',
  `ad` varchar(25) NOT NULL,
  `soyad` varchar(25) NOT NULL,
  `mail` varchar(70) NOT NULL,
  `sifre` varchar(16) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `Kullanicilar`
--

INSERT INTO `Kullanicilar` (`id`, `admin`, `ad`, `soyad`, `mail`, `sifre`) VALUES
(1, b'1', 'Reber', 'Sincar', 'reber@mail.com', '123456');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Markalar`
--

CREATE TABLE `Markalar` (
  `id` tinyint(3) UNSIGNED NOT NULL,
  `marka_ad` varchar(30) NOT NULL,
  `logo` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `Markalar`
--

INSERT INTO `Markalar` (`id`, `marka_ad`, `logo`) VALUES
(1, 'Alfa Romeo', 'Logo/alfaromeo.png'),
(2, 'Audi', 'Logo/audi.png'),
(3, 'BMW', 'Logo/bmw.png'),
(5, 'Fiat', 'Logo/fiat.png'),
(6, 'Ford', 'Logo/ford.png'),
(7, 'Honda', 'Logo/honda.png'),
(8, 'Hyundai', 'Logo/hyundai.png'),
(9, 'Mercedes', 'Logo/mercedes.png'),
(10, 'Opel', 'Logo/opel.png'),
(12, 'Renault', 'Logo/renault.png'),
(13, 'Wolkvagen', 'Logo/wolksvagen.png');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Modeller`
--

CREATE TABLE `Modeller` (
  `id` smallint(8) UNSIGNED NOT NULL,
  `marka_id` int(11) NOT NULL,
  `model_ad` varchar(30) NOT NULL,
  `model_yil` varchar(4) NOT NULL,
  `yakit_id` tinyint(3) NOT NULL,
  `vites_id` tinyint(3) NOT NULL,
  `vites_kademe` tinyint(3) NOT NULL,
  `motor_gucu` float NOT NULL,
  `silindir_sayisi` tinyint(3) NOT NULL,
  `koltuk_sayisi` tinyint(3) NOT NULL,
  `kapi_sayisi` tinyint(3) NOT NULL,
  `yakit_depo_hacmi` tinyint(3) NOT NULL,
  `agirlik` smallint(8) NOT NULL,
  `hiz` smallint(4) NOT NULL,
  `fiyat` int(11) NOT NULL,
  `puan` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `Modeller`
--

INSERT INTO `Modeller` (`id`, `marka_id`, `model_ad`, `model_yil`, `yakit_id`, `vites_id`, `vites_kademe`, `motor_gucu`, `silindir_sayisi`, `koltuk_sayisi`, `kapi_sayisi`, `yakit_depo_hacmi`, `agirlik`, `hiz`, `fiyat`, `puan`) VALUES
(1, 1, 'Guilia', '2018', 1, 1, 8, 2, 4, 5, 4, 58, 1429, 235, 580000, 0),
(2, 2, 'A5 Coupe', '2018', 1, 1, 7, 1.4, 4, 4, 2, 54, 1465, 216, 614000, 80),
(3, 3, '320d', '2018', 2, 1, 6, 2, 4, 5, 4, 57, 1420, 235, 330000, 0),
(4, 5, 'Egea', '2018', 2, 3, 6, 1.4, 4, 5, 4, 45, 1150, 180, 81900, 0),
(5, 6, 'Focus', '2018', 1, 3, 6, 1.6, 4, 5, 4, 53, 1289, 192, 114000, 0),
(6, 7, 'Civic', '2018', 1, 3, 7, 1.6, 4, 5, 4, 47, 1301, 205, 120800, 0),
(7, 8, 'Elantra', '2018', 2, 3, 7, 1.6, 4, 5, 4, 50, 1390, 194, 138300, 0),
(8, 9, 'CLA 180', '2018', 2, 3, 8, 1.5, 4, 5, 4, 50, 1475, 205, 286400, 100),
(9, 10, 'Insignia', '2018', 1, 1, 7, 1.5, 4, 5, 4, 62, 1397, 218, 163000, 0),
(10, 12, 'Talisman', '2018', 2, 3, 7, 1.6, 4, 5, 4, 52, 1430, 205, 187450, 0),
(11, 13, 'Passat', '2018', 1, 3, 7, 1.4, 4, 5, 4, 66, 1367, 208, 150600, 80);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Puanlar`
--

CREATE TABLE `Puanlar` (
  `id` int(11) UNSIGNED NOT NULL,
  `user_id` int(11) NOT NULL,
  `marka_id` int(11) NOT NULL,
  `model_id` int(11) NOT NULL,
  `puan` smallint(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `Puanlar`
--

INSERT INTO `Puanlar` (`id`, `user_id`, `marka_id`, `model_id`, `puan`) VALUES
(1, 1, 9, 8, 100),
(2, 1, 13, 11, 80),
(3, 1, 2, 2, 80);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Resimler`
--

CREATE TABLE `Resimler` (
  `id` int(11) UNSIGNED NOT NULL,
  `marka_id` tinyint(3) NOT NULL,
  `model_id` smallint(8) NOT NULL,
  `resim` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `Resimler`
--

INSERT INTO `Resimler` (`id`, `marka_id`, `model_id`, `resim`) VALUES
(1, 1, 1, 'images/796926d191128096398t4479372.jpg'),
(2, 1, 1, 'images/279540b8631037809475u683821e.jpg'),
(3, 1, 1, 'images/780648v22701q792252w167578d.jpg'),
(4, 2, 2, 'images/5200627414185v999275b683891s.jpg'),
(5, 3, 3, 'images/6382460467856e592514j673958c.jpg'),
(6, 3, 3, 'images/477108j790861z440782o4262276.jpg'),
(7, 3, 3, 'images/8674412875394a8290004972494p.jpg'),
(8, 5, 4, 'images/1371528829524d885988l760031i.jpg'),
(9, 5, 4, 'images/8475279921505f775757l7887770.jpg'),
(10, 6, 5, 'images/146482k238688l258983n7401735.jpg'),
(11, 6, 5, 'images/717000m413031j709592d248595u.jpg'),
(12, 7, 6, 'images/5960343230764h666025z6258i.jpg'),
(13, 7, 6, 'images/218683x953595z523227q247305o.jpg'),
(14, 8, 7, 'images/50450105351153960248730999k.jpg'),
(15, 9, 8, 'images/59267907448097994253p722931m.jpg'),
(16, 9, 8, 'images/321047k8314321180539r865239q.jpg'),
(17, 9, 8, 'images/668935r900666r626620y5684078.jpg'),
(18, 9, 8, 'images/289536m43091682377989175094t.jpg'),
(19, 10, 9, 'images/484573x3751h625885q97010y.jpg'),
(20, 10, 9, 'images/292047x957449h678880t195622c.jpg'),
(21, 10, 9, 'images/5843834103495867620o421392c.jpg'),
(22, 12, 10, 'images/300208u565133j79364q368484k.jpg'),
(23, 12, 10, 'images/660131d42766a989186232949e.jpg'),
(24, 12, 10, 'images/682197863552q13087394916l.jpg'),
(25, 13, 11, 'images/1554638808475987031595802407.jpg'),
(26, 13, 11, 'images/82176670853068856013725479j.jpg'),
(27, 13, 11, 'images/449250r831749j9080233939135z.jpg'),
(28, 2, 2, 'images/641667j7576599128421213366.jpg');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Videolar`
--

CREATE TABLE `Videolar` (
  `id` smallint(8) UNSIGNED NOT NULL,
  `marka_id` tinyint(3) NOT NULL,
  `model_id` smallint(8) NOT NULL,
  `reklam` varchar(255) NOT NULL,
  `inceleme` varchar(255) NOT NULL,
  `kazatest` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Vitesler`
--

CREATE TABLE `Vitesler` (
  `id` tinyint(3) UNSIGNED NOT NULL,
  `vites_turu` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `Vitesler`
--

INSERT INTO `Vitesler` (`id`, `vites_turu`) VALUES
(1, 'Otomatik'),
(2, 'Yari Otomatik'),
(3, 'Manuel');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Yakitlar`
--

CREATE TABLE `Yakitlar` (
  `id` int(10) UNSIGNED NOT NULL,
  `yakit_turu` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `Yakitlar`
--

INSERT INTO `Yakitlar` (`id`, `yakit_turu`) VALUES
(1, 'Benzin'),
(2, 'Dizel'),
(3, 'Otogaz + Benzin'),
(4, 'Elektrik + Benzin'),
(5, 'Elektrik');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `Kullanicilar`
--
ALTER TABLE `Kullanicilar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Markalar`
--
ALTER TABLE `Markalar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Modeller`
--
ALTER TABLE `Modeller`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Puanlar`
--
ALTER TABLE `Puanlar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Resimler`
--
ALTER TABLE `Resimler`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Videolar`
--
ALTER TABLE `Videolar`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Vitesler`
--
ALTER TABLE `Vitesler`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `Yakitlar`
--
ALTER TABLE `Yakitlar`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `Kullanicilar`
--
ALTER TABLE `Kullanicilar`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Tablo için AUTO_INCREMENT değeri `Markalar`
--
ALTER TABLE `Markalar`
  MODIFY `id` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Tablo için AUTO_INCREMENT değeri `Modeller`
--
ALTER TABLE `Modeller`
  MODIFY `id` smallint(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Tablo için AUTO_INCREMENT değeri `Puanlar`
--
ALTER TABLE `Puanlar`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Tablo için AUTO_INCREMENT değeri `Resimler`
--
ALTER TABLE `Resimler`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- Tablo için AUTO_INCREMENT değeri `Videolar`
--
ALTER TABLE `Videolar`
  MODIFY `id` smallint(8) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Tablo için AUTO_INCREMENT değeri `Vitesler`
--
ALTER TABLE `Vitesler`
  MODIFY `id` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Tablo için AUTO_INCREMENT değeri `Yakitlar`
--
ALTER TABLE `Yakitlar`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
