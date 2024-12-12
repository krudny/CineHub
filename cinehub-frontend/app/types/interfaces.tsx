import { ReactNode } from "react";

export interface HeroProps {
  url: string;
  title: string;
  rating: number;
  duration: string;
  year: number;
  description: string;
}

export interface LayoutProps {
  children: ReactNode;
}

export interface AuthFormProps {
  id: string;
  type: string;
  placeholder: string;
}

export interface AuthProps {
  welcome: string;
  greeting: string;
  actionText: string;
  actionLink: string;
  formFields: AuthFormProps[];
  buttonText: string;
}
