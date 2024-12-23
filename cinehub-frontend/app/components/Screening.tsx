"use client";

import useSWR from "swr";
import { MovieResponse } from "@/app/types/interfaces";
import { useEffect, useState } from "react";

interface Screening {
  screeningId: number;
  startDate: string;
  room: { name: string };
  price: number;
}

export default function Screening(movie: MovieResponse) {
  const fetcher = (url: string) => fetch(url).then((res) => res.json());
  const { data } = useSWR<Screening[]>(
    `http://localhost:8080/screenings?movieId=${movie.movieId}`,
    fetcher,
  );

  const [selectedDate, setSelectedDate] = useState<string>("");

  const formatDate = (isoDate: string) => {
    const date = new Date(isoDate);
    const options: Intl.DateTimeFormatOptions = {
      weekday: "long",
      day: "2-digit",
      month: "2-digit",
    };
    return date.toLocaleDateString("en-US", options);
  };

  const uniqueDates = data
    ? Array.from(
        new Set(data.map((screening) => screening.startDate.split("T")[0])),
      ).sort()
    : [];

  useEffect(() => {
    if (uniqueDates.length > 0 && !selectedDate) {
      setSelectedDate(uniqueDates[0]);
    }
  }, [uniqueDates, selectedDate]);

  return (
    <div className="flex flex-col gap-4 ">
      {uniqueDates.length > 0 ? (
        <>
          <div className="flex items-center gap-x-4 mt-5">
            <p className="font-bold">Date:</p>
            <select
              value={selectedDate}
              onChange={(e) => setSelectedDate(e.target.value)}
              className="text-neutral-100 rounded bg-transparent focus:outline-none transition-all hover:cursor-pointer"
            >
              {uniqueDates.map((date) => (
                <option
                  key={date}
                  value={date}
                  className="bg-neutral-100 text-neutral-700"
                >
                  {formatDate(date)}
                </option>
              ))}
            </select>
          </div>

          <div className="flex items-center gap-x-4 mt-2">
            <p className="font-bold">Time:</p>
            <div className="flex items-center gap-x-4">
              {data
                ?.filter((screening) =>
                  screening.startDate.startsWith(selectedDate),
                )
                .map((screening) => (
                  <p key={screening.screeningId}>
                    {new Date(screening.startDate).toLocaleTimeString([], {
                      hour: "2-digit",
                      minute: "2-digit",
                    })}
                  </p>
                ))}
            </div>
          </div>

          {/* Choose Seat Button */}
          <button className="flex w-fit mt-3 justify-center items-center px-4 py-3 text-md bg-orange-500 rounded-3xl transition duration-200 ease-in-out hover:bg-orange-600">
            <p className="text-zinc-900 font-bold text-md lg:text-lg">
              Choose seat
            </p>
          </button>
        </>
      ) : (
        <p className="text-lg text-neutral-500 mt-10">
          No screenings available.
        </p>
      )}
    </div>
  );
}
