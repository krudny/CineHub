import type { Config } from "tailwindcss";

export default {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      gridTemplateColumns: {
        5: "repeat(5, minmax(0, 1fr))",
        7: "repeat(7, minmax(0, 1fr))",
        9: "repeat(9, minmax(0, 1fr))",
        11: "repeat(11, minmax(0, 1fr))",
      },
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
