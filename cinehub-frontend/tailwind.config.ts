import type { Config } from "tailwindcss";

export default {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        raleway: ["Raleway", "sans-serif"],
        oswald: ["Oswald", "sans-serif"],
      },
      colors: {
        background: "var(--background)",
        foreground: "var(--foreground)",
      },
      screens: {
        "h-md": { raw: "(min-height: 768px)" },
        "h-lg": { raw: "(min-height: 1024px)" },
      },
    },
  },
  plugins: [],
} satisfies Config;
