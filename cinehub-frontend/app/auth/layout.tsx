import Navbar from "@/app/components/Navbar";
import { LayoutProps } from "@/app/types/interfaces";

export default function Page({ children }: LayoutProps) {
  return (
    <div className="w-screen min-h-screen flex flex-col flex-1 bg-zinc-900">
      <Navbar />

      {children}

      <div className="w-full flex justify-center mt-auto">
        <div className="w-96 text-center">
          <p className="text-orange-500 font-oswald font-bold mb-4">
            CineHub - Your movie center
          </p>
        </div>
      </div>
    </div>
  );
}
