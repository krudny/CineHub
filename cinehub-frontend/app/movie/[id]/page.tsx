import Navbar from "@/app/components/Navbar";
import {MovieResponse} from "@/app/types/interfaces";
import MovieInfo from "@/app/components/MovieInfo";
import TicketSummary from "@/app/components/TicketSummary";



async function getMovie(id: string) {
  const res = await fetch(`http://localhost:8080/movies/${id}`);
  return res.json();
}

async function getDiscountsNames() {
  const res = await fetch(`http://localhost:8080/discounts/names`)
  return res.json();
}


export default async function MoviePage({ params }: { params: { id: string } })  {
  const movie: MovieResponse = await getMovie(params.id);
  const discounts: string[] = await getDiscountsNames();


  return (
    <div className="bg-zinc-900 max-w-screen">
      <Navbar />

      <MovieInfo {...movie} />

      <div className="max-w-7xl mx-auto mt-12 flex pb-56">
        <TicketSummary discounts={discounts} />

        <div className="w-full  p-2">
          <div className="bg-zinc-800 h-full rounded-xl p-6 text-neutral-100">
            <p>seats</p>
          </div>
        </div>
      </div>
    </div>
  );
}
