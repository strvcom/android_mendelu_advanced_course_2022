import 'package:flutter/material.dart';
import 'package:xkcd/feature_landing/landing_page.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(brightness: Brightness.light, useMaterial3: true, primarySwatch: Colors.red),
      darkTheme: ThemeData(brightness: Brightness.dark, useMaterial3: true, primarySwatch: Colors.red),
      themeMode: ThemeMode.system,
      // getPages: Navigation.pages,
      // initialBinding: BindingsBuilder(() {
      //   Get.lazyPut(() => XKCDProvider());
      // }),
      // initialRoute: "/landing",
      home: const LandingPage(),
    );
  }
}

// class Navigation {
//   static final pages = [
//     GetPage(
//       name: "/landing",
//       page: () => const LandingPage(),
//       transition: Transition.fadeIn,
//     ),
//     GetPage(
//       name: "/home",
//       page: () => const MyHomePage(),
//       transition: Transition.fadeIn,
//     ),
//     GetPage(
//       name: "/getx_home",
//       page: () => const GetXHomePage(),
//       binding: BindingsBuilder(() {
//         Get.put(GetXHomeController());
//       }),
//       transition: Transition.fadeIn,
//     ),
//     GetPage(
//       name: "/random",
//       page: () => const RandomComicsPage(),
//       binding: BindingsBuilder(() {
//         Get.put(RandomComicsContoller());
//       }),
//       transition: Transition.fadeIn,
//     ),
//   ];
// }
