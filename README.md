# Ktor with Tailwind

A sample Ktor application that uses kotlinx.html and Tailwind to generate websites.

## Highlights

- The project uses [Tailwind CLI](https://tailwindcss.com/docs/installation) to generate a stylesheet that consists only of the css classes referenced in the Kotlin code.
  - This results in much smaller stylesheet size compared to directly using the [Tailwind CDN](https://tailwindcss.com/docs/installation/play-cdn).
- The `tailwindcss` cli is executed as part of the Gradle build with `node-gradle` plugin. See the custom `NpxTask` task in [build.gradle.kts](build.gradle.kts).   
- [tailwind.config.js](tailwind.config.js) configures a path to the source files which the tailwind cli scans when generating the stylesheet.
  - There is no additional magic needed even though the source files are written in Kotlin: the default config works almost out-of-the box in simple scenarios.