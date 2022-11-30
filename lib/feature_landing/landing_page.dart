import 'package:flutter/material.dart';

class LandingPage extends StatelessWidget {
  const LandingPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Landing page"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: const [
            Text("Welcome!"),
            // ElevatedButton(
            //   onPressed: () {
            //     Get.toNamed("/home");
            //   },
            //   child: const Text("To Home"),
            // ),
            // ElevatedButton(
            //   onPressed: () {
            //     Get.toNamed("/getx_home");
            //   },
            //   child: const Text("To GetX Home"),
            // ),
            // ElevatedButton(
            //   onPressed: () {
            //     Get.toNamed("/random");
            //   },
            //   child: const Text("To Random Comics"),
            // ),
          ],
        ),
      ),
    );
  }
}
