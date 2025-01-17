"use client";

import Navbar from "@/app/components/Navbar";
import FilmsGrid from "@/app/components/FilmsGrid";
import { useEffect, useState } from "react";

export default function Page() {
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const fetchTotalPages = async () => {
      const response = await fetch(`http://localhost:8080/movies/page?page=0`);
      const data = await response.json();
      setTotalPages(data.totalPages);
    };
    fetchTotalPages();
  }, []);

  const handleNextPage = () => setCurrentPage((prev) => prev + 1);
  const handlePreviousPage = () =>
    setCurrentPage((prev) => Math.max(prev - 1, 0));

  return (
    <div className="bg-zinc-900 max-w-screen min-h-screen text-neutral-100">
      <Navbar />
      <div className="bg-neutral-100 text-zinc-900 text-center max-w-2xl mx-auto rounded-lg mt-10">
        TODO searchbar
      </div>

      <FilmsGrid page={currentPage} />

      <div className="flex justify-center items-center gap-4 py-4">
        <button
          className="flex w-24 my-3 justify-center items-center px-4 py-3 text-md bg-orange-500 hover:bg-orange-600 rounded-3xl transition duration-200 ease-in-out"
          onClick={handlePreviousPage}
        >
          <p className="text-zinc-900 font-bold text-md lg:text-lg">Previous</p>
        </button>
        <span>
          Page: {currentPage + 1} / {totalPages}
        </span>

        <button
          className="flex w-24 my-3 justify-center items-center px-4 py-3 text-md bg-orange-500 hover:bg-orange-600 rounded-3xl transition duration-200 ease-in-out"
          onClick={handleNextPage}
          disabled={currentPage >= totalPages - 1}
        >
          <p className="text-zinc-900 font-bold text-md lg:text-lg">Next</p>
        </button>
      </div>
    </div>
  );
}
