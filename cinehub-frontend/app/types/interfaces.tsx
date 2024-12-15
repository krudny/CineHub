import { ReactNode } from "react";

export interface MovieResponse {
  movieId: number;
  genre: {
    genreId: number;
    name: string;
  };
  title: string;
  description: string;
  duration: number;
  production: string;
  director: string;
  publishDate: string;
  bg_img: string;
  thumbnail_img: string;
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
