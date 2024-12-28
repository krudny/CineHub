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

export interface Room {
  roomId: number;
  name: string;
}

export interface SeatProps {
  seatId: number;
  seatNumber: number;
  isReserved?: boolean;
  onReserve: (seatId: number) => void;
}

export interface ReservationProps {
  searchParams: {
    title: string;
    fullDate: string;
    room: string;
  };
}
