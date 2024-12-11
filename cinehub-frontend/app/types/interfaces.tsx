import { ReactNode } from "react";

export default interface HeroProps {
  url: string;
  title: string;
  rating: number;
  duration: string;
  year: number;
  description: string;
}

export default interface LayoutProps {
  children: ReactNode;
}
