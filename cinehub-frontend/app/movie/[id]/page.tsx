import Navbar from "@/app/components/Navbar";
import { MovieResponse } from "@/app/types/interfaces";
import MovieInfo from "@/app/components/MovieInfo";

async function getMovie(id: string) {
  const res = await fetch(`http://localhost:8080/movies/${id}`, {
    cache: "force-cache",
  });
  return res.json();
}

export default async function MoviePage({
  params,
}: {
  params: { id: string };
}) {
  const { id } = await params;
  const movie: MovieResponse = await getMovie(id);

  return (
    <div className="bg-zinc-900 max-w-screen min-h-screen">
      <Navbar />
      <MovieInfo {...movie} />
    </div>
  );
}
