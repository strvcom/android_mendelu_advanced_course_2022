import 'package:flutter_fimber/flutter_fimber.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:xkcd/app.dart';

void main() {
  // WidgetsBinding widgetsBinding = WidgetsFlutterBinding.ensureInitialized();
  // FlutterNativeSplash.preserve(widgetsBinding: widgetsBinding);
  // To remove the splash: FlutterNativeSplash.remove();

  // Enable Debug Logging
  if (kDebugMode || kProfileMode) {
    // Fimber.plantTree(DebugTree(useColors: true));
    Fimber.plantTree(
      CustomFormatTree(
        logFormat: "${CustomFormatTree.timeStampToken} | ${CustomFormatTree.messageToken}",
        useColors: true,
        logLevels: ["V", "D", "I", "W", "E"],
      ),
    );
  }

  runApp(const MyApp());
}
